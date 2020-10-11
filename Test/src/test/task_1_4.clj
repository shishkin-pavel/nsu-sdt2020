(ns test.task_1_4)

(defn get-pair [word alph]
  (map #(cons %1 word) (filter #(not= (first word) %1) alph)))

(defn mix [alph words]
  (reduce concat (map #(get-pair %1 alph) words)))

(defn my-permut [alph n]
  (reduce (fn [words _] (mix alph words)) `(()) (range n)))

(defn -main [& _]
  (prn (my-permut `(:a :b "c") 3)))