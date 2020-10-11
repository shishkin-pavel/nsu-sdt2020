(ns test.task_1_1)

(defn get-pair [word alph]
  (if (= 0 (count alph))
    ()
    (if (= (first word) (first alph))
      (get-pair word (rest alph))
      (cons (cons (first alph) word) (get-pair word (rest alph))))))

(defn mix [alph words]
  (if (= 0 (count words))
    ()
    (concat (mix alph (rest words)) (get-pair (first words) alph))))

(defn repeat-n-times [alph n]
  (if (= 0 n)
    `(())
    (mix alph (repeat-n-times alph (dec n)))))

(defn my-permut [alph n]
  (repeat-n-times alph n))

(defn -main [& _]
  (prn (my-permut `(:a :b "c") 3)))