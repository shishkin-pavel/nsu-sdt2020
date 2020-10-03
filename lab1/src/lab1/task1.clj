(ns lab1.task1)

(defn add-char [c xs]
    (if (> (count xs) 0)
        (if (= c (first xs))
            (add-char c (rest xs))
            (concat
                (list (list c (first xs)))
                (add-char c (rest xs))))
        xs))

(defn concat-string-list [xs ys]
    (if (> (count xs) 0) 
        (concat
            (add-char (first xs) ys)
            (concat-string-list (rest xs) ys))
        xs))

(defn task1
    ([xs n] 
        (if (> n 0)
            (task1 xs (dec n) xs)
            (list)))
    ([xs n acc]
        (if (> n 0)
            (task1 xs (dec n) (concat-string-list xs acc))
            acc)))
