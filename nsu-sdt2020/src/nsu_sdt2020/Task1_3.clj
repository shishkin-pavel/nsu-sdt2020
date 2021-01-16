(ns Task1_3)
;Определить функции my-map и my-filter, аналогичные map (для одного списка) и filter, выразив
;их через reduce и базовые операции над списками (cons, first, concat и т.п.)

;   my_map - отображение, выполняется заданная операция для каждого атома =>новый список
;   my_filter - фильтрация, создается новая коллекция из элементов, которые  удовлетворяют предикаату

(defn fn-for-map [symbol choices]
  (loop [ch choices, acc '()]
    (if (= (count ch) 0)
      acc
      (recur (rest ch) (concat acc (list (concat (first ch) symbol)))))))

(defn predicat-not-equals [result]
  (not= (first result) (first (rest result))))

(defn predicat-equals [result]
  (= (first result) (first (rest result))))

(defn my-filter [p coll]
  (reduce
    (fn [acc c] (if (p c) (conj acc c) acc))
    []
    coll))

(defn my-map [f res choices]
  (reduce
    (fn [acc c] (concat acc (f c choices)))
    []
    res))


(defn task1 [res0a res0b n_left choices]
  (if (= n_left 0)
    res0a
    (do
      (println)
      (println "New Round Arguments [" (- 5 n_left) "]:   " res0a res0b n_left choices)

      (def res2a (my-map fn-for-map res0a choices))
      (println "my-map[res0a]            :   " res2a)

      (def res3a (my-filter predicat-not-equals res2a))
      (println "my-filter[predicat1]     :   " res3a)

      (def res2b (my-map fn-for-map res0b choices))
      (println "my-map[res0b]            :   " res2b)
      (def res3b (my-filter predicat-equals res2b))
      (println "my-filter[predicat1]     :   " res3b)

      (if (not= res0a '(()))
        (recur res3a res3b (dec n_left) choices)
        (recur res3a res3a (dec n_left) choices))
  )))

(defn -main
  [& args]
  (task1 '(()) '(()) 4 '((a) (b) (c) (d)))
  )