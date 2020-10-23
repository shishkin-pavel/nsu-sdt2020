(ns lab1.task3)

(defn my-map [f xs]
    (reduce (fn [ys y] (concat ys (list (f y)))) `() xs))

(defn my-filter [f xs]
    (reduce (fn [ys y] (if (f y) (concat ys (list y)) ys)) `() xs))
