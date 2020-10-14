;; aka task 2.1 
(ns integral-memo
    (:gen-class))
(def sum (memoize 
        (fn [func step b] (if (= b 0.0) 0.0 (+ (sum func step (- b step)) (/ (* (+ (func b) (func (- b step))) step) 2))))))
(def negsum (memoize
        (fn [func step b] (if (= b 0.0) 0.0 (+ (negsum func step (+ b step)) (/ (* (+ (func b) (func (+ b step))) step) 2))))))
(defn integral [func step]
    (fn [b] 
    (if (> b 0)
        (let [diff (- b (* (Math/floor (/ b step)) step))] (+ (sum func step (- b diff)) (/ (* (+ (func b) (func (- b diff))) diff) 2)))
        (let [diff (- b (* (Math/floor (/ b step)) step))] (* (+ (negsum func step (+ b diff)) (/ (* (+ (func b) (func (+ b diff))) diff) 2)) -1)))))