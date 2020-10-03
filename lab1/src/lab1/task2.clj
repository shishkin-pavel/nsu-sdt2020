(ns lab1.task2)

(defn add-char-recur
    ([c xs] (add-char-recur c xs (list)))
    ([c xs acc]
        (if (= (count xs) 0)
            acc
            (if (= c (first xs))
                (recur c (rest xs) acc)
                (recur c (rest xs) (concat acc (list (list c (first xs)))))))))

(defn concat-string-list-recur 
    ([xs ys] (concat-string-list-recur xs ys (list)))
    ([xs ys acc]
        (if (= (count xs) 0)
            acc
            (recur (rest xs) ys (concat acc (add-char-recur (first xs) ys))))))

(defn task2
    ([xs n] 
        (if (> n 0)
            (task2 xs (dec n) xs)
            (list)))
    ([xs n acc]
        (if (= n 0)
            acc
            (recur xs (dec n) (concat-string-list-recur xs acc)))))
