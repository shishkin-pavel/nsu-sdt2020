(ns lab2.lab2_1)

(defn exponentiation [x]
  (* x x))

(defn trap [func x1 x2]
  (* (/ (+ (func x1) (func x2)) 2) (- x2 x1)))

(def memo-solve
  (memoize
    (fn [func end step]
      (if (<= end 0)
        0
        (+ (memo-solve func (- end step) step) (trap func (- end step) end))))))

(println "memo:")
(time (memo-solve exponentiation 100 1))
(time (memo-solve exponentiation 100 1))
(time (memo-solve exponentiation 99 1))
(time (memo-solve exponentiation 101 1))
(time (memo-solve exponentiation 16 1))
(time (memo-solve exponentiation 33 1))
