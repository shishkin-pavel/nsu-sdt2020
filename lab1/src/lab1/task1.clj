(ns lab1.task1)

(defn wrap-char [xs]
    (if (> (count xs) 0)
        (cons (list (first xs)) (wrap-char (rest xs)))
        xs))

(defn add-char [c xs]
    (if (> (count xs) 0)
        (if (= c (first (first xs)))
            (add-char c (rest xs))
            (cons
                (cons c (first xs))
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
            (task1 xs (dec n) (wrap-char xs))
            (list)))
    ([xs n acc]
        (if (> n 0)
            (task1 xs (dec n) (concat-string-list xs acc))
            acc)))
