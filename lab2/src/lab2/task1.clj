(ns lab2.task1)

(defn trapeze-area [f a b]
    (* (/ (+ (f a) (f b)) 2.0) (- b a)))

(def sum-memoize
    (memoize
        (fn [f dx n]
            (if (> n 0)
                (+ 
                    (sum-memoize f dx (dec n))
                    (trapeze-area f (* dx (dec n)) (* dx n)))
                0.0))))

(defn task1 [f dx b]
    (if (> dx 0)
        (let [n (int (/ b dx))]
            (+
                (sum-memoize f dx n)
                (trapeze-area f (* n dx) b)))
        0.0))

