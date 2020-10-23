(ns lab2.task2
    (:use lab2.common))

(defn iterate-integr [f dx]
    (map 
        first 
        (iterate
            (fn [[s n]]
                [(+ s (trapeze-area f (* dx n) (* dx (inc n)))) (inc n)])
                [0.0 0.0])))

(defn task2 [f dx b]
    (if (> dx 0)
        (let [n (int (/ b dx))]
            (+
                (nth (iterate-integr f dx) n)
                (trapeze-area f (* n dx) b)))
        0.0))
