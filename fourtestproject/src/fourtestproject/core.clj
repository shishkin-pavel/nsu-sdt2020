(ns fourtestproject.core)

(defn trap-area [f a b height]
  (* 0.5 (+ (f a) (f b)) height))

(defn integrate-helper [f step x integrate-rest]
  (:pre [(>= x 0)])
  (if (< x step)
    0
    ; f-x-val = f(x_i)
    ; f-x-left-val = f(x_i-1)
    (let [trarea (trap-area f (* x step) (* (dec x) step) step)]
      (+ trarea (integrate-rest f step (dec x) integrate-rest)))))

(def memoized (memoize integrate-helper))

(defn integrate [f step x]
  (let [tail (int (/ x step)), integr 0]
    (if (not= tail (/ x step))
      (def integr (trap-area f x (* tail step) step)))
    (+ integr (integrate-helper f step (int (/ x step)) memoized))))

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
  (time (integrate test-func 0.1 10.1)))
