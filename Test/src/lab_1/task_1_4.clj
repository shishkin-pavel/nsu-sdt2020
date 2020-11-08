(ns lab_1.task_1_4)

(defn get-pair [word alph]
  (map #(cons %1 word) (filter #(not= (first word) %1) alph)))

(defn mix [alph words]
  (mapcat #(get-pair %1 alph) words))

(defn my-permut-with-reduce [alph n]
  (reduce (fn [words _] (mix alph words)) `(()) (range n)))

(defn my-permut-with-iterate [alph n]
  (nth (iterate (partial mix alph) `(())) n))

(defn -main [& _]
  (prn (my-permut-with-iterate `(:a :b "c") 3)))