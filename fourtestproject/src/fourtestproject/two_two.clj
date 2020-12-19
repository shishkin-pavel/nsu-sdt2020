(ns fourtestproject.two-two)

(defn trap-area [f a b height]
  (* 0.5 (+ (f a) (f b)) height))

(defn integrate-helper [f arr count tail step integrate-rest]
  (if (< count 0)
    0
    (let [tail-left (nth arr count), trarea (trap-area f tail tail-left step)]
      (+ trarea (integrate-rest f arr (dec count) tail-left step integrate-rest)))))

(def memoized (memoize integrate-helper))

(defn integrate [f step x]
  (let [count (int (/ x step)), arr (take (inc count) (iterate (partial + step) 0)), tail (nth arr count), integr 0]
    (if (not= tail x)
      (def integr (trap-area f x tail step)))
    (+ integr (integrate-helper f arr (dec count) tail step memoized))))

(defn test-func [x]
  (Math/sin (Math/sin (Math/sin (Math/sin (Math/sin x))))))

(defn -main [& args]
  (println "Mnogo vremeni:")
  (time (integrate test-func 0.1 5))
  (println "Malo vremeni:")
  (time (integrate test-func 0.1 5.1))
  (println "Mnogo vremeni:")
  (time (integrate test-func 0.1 10))
  (println "Malo vremeni:")
  (time (integrate test-func 0.1 10.1))
  )
