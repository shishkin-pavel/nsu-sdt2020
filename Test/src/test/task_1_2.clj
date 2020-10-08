(ns test.task_1_2)

(defn get-pair [sym alph res]
  (if (= 0 (count alph))
    res
    (if (.contains (str sym) (str (first alph)))
      (recur sym (rest alph) res)
      (recur sym (rest alph) (cons (apply str (cons sym (list (first alph)))) res)))))

(defn mix [alph tmp res]
  (if (= 0 (count tmp))
    res
    (recur alph (rest tmp) (concat (get-pair (first tmp) alph `()) res))))

(defn repeat-n-times [alph n res]
  (if (= 1 n)
    res
    (recur alph (dec n) (mix alph res `()))))

(defn my-permut [alph n]
  (repeat-n-times alph n alph))


(defn -main [& _]
  (prn (my-permut `(:a (:b 1) "c") 3)))