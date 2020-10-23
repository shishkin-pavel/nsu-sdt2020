(ns lab2.common)

(defn trapeze-area [f a b]
    (* (/ (+ (f a) (f b)) 2.0) (- b a)))
