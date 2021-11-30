(ns concurrency-demo.core
  (:require [clojure.core.async :refer [chan close! thread go go-loop
                                        take! put! <! >! <!! >!!
                                        timeout
                                        buffer sliding-buffer dropping-buffer
                                        alt! alts!
                                        mix admix unmix mult tap untap]])
  (:use [clojure.pprint :only [pprint]]))

(defn start-logger [fname]
  (let [log-chan (chan)]
    (go-loop []
      (spit fname (str (<! log-chan)) :append true)
      (recur))
    (put! log-chan "")
    log-chan))

(let [log-chan (start-logger "out.log")]
  (defn log [& vals]
    (>!! log-chan (apply str (concat (interpose " " vals) '("\n"))))))

(comment

  (def c (chan))

  (log "---------- неблокирующие операции с коллбеками")
  (put! c "hello from chan")
  (take! c (fn [msg] (log msg)))

  (do
    (log "before sending message")
    (put! c "hello sent with callback"
          (fn [res] (log "message finally sent, result:" res))))
  (take! c (fn [msg] (log msg)))


  (log "---------- пытаемся уйти от коллбеков: взятие значения")
  (defn takep [c]
    (let [p (promise)]
      (take! c (fn [v] (deliver p v)))
      @p))

  (future (log "got:" (takep c)))
  (put! c 42 (fn [res] (log "done" res)))


  (log "---------- пытаемся уйти от коллбеков: предоставление значения")
  (defn putp [c val]
    (let [p (promise)]
      (put! c val (fn [res] (deliver p res)))
      @p))

  (future (log "done" (putp c "hello from another thread")))
  (future (log "got:" (takep c)))


  (log "---------- то же самое в стандартной библиотеке: <!! & >!!")
  (future (log "done sending via standard lib function:" (>!! c "some msg")))
  (future (log "received:" (<!! c)))

  ;; with caution!
  (<!! c)


  (log "---------- избавляемся от future:")
  (thread [1 2 3])
  (<!! (thread [1 2 3]))

  (thread (log "done sending via standard lib function:" (>!! c "some msg")))
  (thread (log "received:" (<!! c)))


  (log "---------- легковесные потоки вместо настоящих")
  (go 123)
  (<!! (go 123))

  (log "---------- неблокирующие операции <! & >!")
  (go (log "incoming data:" (<! c)))
  (go (log "sending numbers:" (>! c [4 5 6 7])))

  (pprint (macroexpand '(go "hello?")))

  (def ch (chan))
  (def go-ch (go
               (log "before putting")
               (>! ch 1)
               (log "after first")
               (>! ch 2)
               (log "done putting")
               3))
  (go (log "returned from `go-ch`:" (<! go-ch)))
  (go (log "returned from `ch`: " (<! ch)))


  (log "---------- закрытие канала")
  (def c (chan))
  (go (dotimes [i 3]
        (>! c i)))
  (<!! c)
  (close! c)


  (log "---------- буфер фиксированного размера")
  (def c1 (chan 1))
  (def go-ch (go
               (log "before putting")
               (>! c1 1)
               (log "after first")
               (>! c1 2)
               (log "done putting")
               3))
  (go (log "returned from `go-ch`:" (<! go-ch)))
  (go (log "returned from `ch`: " (<! c1)))


  (log "---------- таймауры")
  (<!! (timeout 2000))
  (time (<!! (timeout 250)))

  (go
    (log 1)
    (<! (timeout 1000))
    (log 2)
    (<! (timeout 1000))
    (log 3))

  (go (dotimes [i 4]
        (log i)
        (<! (timeout 1000))))


  (log "---------- буфер, выбрасывающий новые значения")
  (def c-dropping (chan (dropping-buffer 1)))
  (def go-ch (go (dotimes [i 8]
                   (log "current item:" i)
                   (>! c-dropping i)
                   (<! (timeout 1000)))
                 :finished))
  (go (log "returned from `go-ch`:" (<! go-ch)))

  (go (log "returned from `ch`: " (<! c-dropping)))


  (log "---------- буфер, выбрасывающий устаревшие значения")
  (def c-sliding (chan (sliding-buffer 1)))
  (def go-ch (go (dotimes [i 8]
                   (log "current item:" i)
                   (>! c-sliding i)
                   (<! (timeout 1000)))
                 :finished))
  (go (log "returned from `go-ch`:" (<! go-ch)))

  (go (log "returned from `ch`: " (<! c-sliding)))


  (log "---------- селективные операции")
  (def a (chan))
  (def b (chan))

  (go (log (alt!
             a ([a-msg] ["a-msg:" a-msg])
             b ([b-msg] :nothing))))

  (>!! a "foo")
  (>!! b "bar")

  (go (log (alts! [a b])))

  (go (alt!
        a ([msg] (log "a says:" msg))
        (timeout 3000) ([_] (log :timed-out))))

  (def c (chan))

  (go (alt!
        [[a "some message to a"]] ([_]   (log "sent message to a"))
        [b c]                     ([msg] (log "received message from b or c:" msg))
        (timeout 3000)            ([_]   (log :timed-out))))

  (go (log "a received:" (<! a)))
  (go (>! b "hi!"))
  (go (>! c "i dont like ya"))

  (go (dotimes [i 3]
        (>! a ["a" i])))
  (go (dotimes [i 2]
        (>! b ["b" i])))

  (go (let [[msg ch] (alts! [a b])]
        (log msg "from channel" ch)))

  (go (let [[msg _ch] (alts! [a b] :priority true)]
        (log msg)))

  (go (let [[msg ch] (alts! [a b] :default "nothin is ready!")]
        (log msg ch)))


  (log "---------- циклы")
  (def c1 (chan))
  (go (loop []
        (log "c1" (<! c1))
        (recur)))

  (>!! c1 "alice")
  (>!! c1 "bob")
  (>!! c1 "carol")

  (def c2 (chan))
  (go-loop []
    (log "c2" (<! c1))
    (recur))

  (>!! c2 1)
  (>!! c2 3)
  (>!! c2 5)

  (defn counter []
    (let [in (chan)
          out (chan)]
      (go-loop [val 0]
        ;; (log "current val:" val)
        (alt!
          in          ([_] (recur (inc val)))
          [[out val]] ([_] (recur val))))
      [in out]))

  (def counter-1 (counter))
  (def c-in (first counter-1))
  (def c-out (second counter-1))
  (go (log "counter val:" (<! c-out)))
  (go (>! c-in :sdf))



  (defn random-timeout [min max]
    (timeout (+ min (rand-int (- max min)))))

  (defn another [[a b] x]
    (if (= a x)
      b
      a))

  (defn philosopher [name left right]
    (go-loop [state :thinking forks {}]
      (log name state (vals forks))
      (condp = state
        :thinking (do
                    (<! (random-timeout 500 1000))
                    (alt!
                      left ([fork]
                            (when fork
                              (recur :single-fork {left fork})))
                      right ([fork]
                             (when fork
                               (recur :single-fork {right fork})))))
        :single-fork (let [[[side my-fork]] (vec forks)
                           not-my-fork-side (another [left right] side)]
                       (alt!
                         not-my-fork-side
                         ([fork]
                          (when fork
                            (recur :eating {side my-fork not-my-fork-side fork})))

                         [[side my-fork]] ([_] (recur :thinking {}))))
        :eating (do
                  (<! (random-timeout 2000 3000))
                  (let [[_res sent-to] (alts! (vec forks))]
                    (recur :single-fork (vec (dissoc forks sent-to))))))))

  (defn start-philosophers []
    (let [names ["Socrates" "Plato" "Aristotle" "Thales" "Empedocles"]
          channels (mapv (fn [_] (chan)) names)]
      (dotimes [i (count names)]
        (put! (channels i) i))
      (doall (map-indexed
              (fn [idx name]
                (philosopher name
                             (channels idx)
                             (channels (mod (inc idx) 5)))) names))
      channels))

  (def p-ch (start-philosophers))

  (doall (map #(close! %) p-ch))

  
  )

(defn -main
  [& args]
  (println "Hello, World!"))