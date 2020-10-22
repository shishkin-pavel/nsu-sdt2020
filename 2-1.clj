(ns lab1.core
  (:gen-class))

(defn calc-trap [f a b]
    ;(println "calc-trap" a b)
  (* (/ (+ (f a) (f b)) 2) (- b a)))

(defn integrate [f end delta]
  (println "integrate" end)
  (if (<= end 0)
    0
    (let [prevv (- end delta)]
            ;(println "integrate prevv" prevv)
      (+
       (integrate-mem f prevv delta)
       (calc-trap f prevv end)))))

(def integrate-mem (memoize integrate))

(letfn [(f [x] (* x x))]
  (println "1")
  (time (integrate-mem f 100 1))
  (time (integrate-mem f 200 1))
  (time (integrate-mem f 300 1))
  (time (integrate-mem f 400 1))
  (time (integrate-mem f 500 1))
  (println "2")
  (time (integrate-mem f 9 1))) ; mem ok

(time (integrate-mem #(* x x) 200 1) ; stack fault
(time (integrate-mem #(* x x) 9 1))  ; not memed
