(ns lab1.task4)

(defn filter-first [c xs]
    (filter (fn [s] (not= c s)) xs))

(defn add-char-map [c xs]
    (map (fn [ys] (list c ys)) (filter-first c xs)))

(defn concat-string-list-map [xs ys]
    (reduce concat `() (map (fn [c] (add-char-map c ys)) xs)))

(defn task4
    ([xs n] 
        (if (> n 0)
            (task4 xs (dec n) xs)
            (list)))
    ([xs n acc]
        (if (= n 0)
            acc
            (recur xs (dec n) (concat-string-list-map xs acc)))))
