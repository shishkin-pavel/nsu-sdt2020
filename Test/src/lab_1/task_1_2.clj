(ns lab_1.task_1_2)

(defn get-pair [word alph]
  (loop [alph alph res ()]
    (if (= 0 (count alph))
      res
      (if (= (first word) (first alph))
        (recur (rest alph) res)
        (recur (rest alph) (cons (cons (first alph) word) res))))))

(defn mix [alph words]
  (loop [words words res ()]
    (if (= 0 (count words))
      res
      (recur (rest words) (concat (get-pair (first words) alph) res)))))

(defn my-permut [alph n]
  (loop [n n res `(())]
    (if (= 0 n)
      res
      (recur (dec n) (mix alph res)))))

(defn -main [& _]
  (prn (my-permut `(:a :b "c") 3)))