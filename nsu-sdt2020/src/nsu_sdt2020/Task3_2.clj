(ns Task3_2)

(defn block-filter [coll pred block-size res]
  (if (empty? coll)  res
                     (recur (drop block-size coll) pred block-size (conj res (future (filter pred (take block-size coll)))))))

(defn parallel-filter [pred block-size coll]
  (->> (block-filter coll pred block-size [])
       (doall)
       (map deref)
))

(defn lazy-parallel-filter [pred block-size group-size coll]
  (let [total-amount (* block-size group-size)]
    (->> (iterate
           (fn [[_ rest]] [(take total-amount rest) (drop total-amount rest)])
           [(take total-amount coll) (drop total-amount coll)])
         (map first)
         (take-while not-empty)
         (map #(lazy-seq (parallel-filter pred block-size %)))
         (flatten))))

(defn compare-results [n description expected-coll actual-coll]
  (print "< Тест № " n " > ")
  (print "  Результат: " (= expected-coll actual-coll))
  (print ".  (" description ")  ")
  (print "  Actual: " actual-coll)
  (println "  Expected: " expected-coll)
  )

(defn -main
  [& args]

  (println "    <Сравнение времени>")
  (println  "Ленивый параллельный фильтр: " )
  (time (lazy-parallel-filter (fn [n] (= 0 (mod n 3))) 50 3 (range 1 1000)))
  (println "Обычный фильтр: ")
  (time (filter (fn [n] (= 0 (mod n 3))) (range 1 1000)))

  (println "\n" "    <Тесты>")
  (compare-results 1 "Пустая коллекция" (filter (fn [n] (= 0 (mod n 3))) '()) (lazy-parallel-filter (fn [n] (= 0 (mod n 3))) 3 5 '()))
  (compare-results 2 "Пустой результат" (filter (fn [n] (= 0 (mod n 3))) (range 1 2)) (lazy-parallel-filter (fn [n] (= 0 (mod n 3))) 3 5 (range 1 2)))
  (compare-results 3 "Одна группа     " (filter (fn [n] (= 0 (mod n 3))) (range 1 101)) (lazy-parallel-filter (fn [n] (= 0 (mod n 3))) 50 2 (range 1 101)))
  (compare-results 4 "Ровно делится   " (filter (fn [n] (= 0 (mod n 3))) (range 1 101)) (lazy-parallel-filter (fn [n] (= 0 (mod n 3))) 25 2 (range 1 101)))
  (compare-results 5 "Неровно делится " (filter (fn [n] (= 0 (mod n 3))) (range 1 101)) (lazy-parallel-filter (fn [n] (= 0 (mod n 3))) 15 2 (range 1 101)))
  (compare-results 6 "Работает с бесконечной последовательностью" (take 100 (filter (fn [n] (= 0 (mod n 3))) (range))) (take 100 (lazy-parallel-filter (fn [n] (= 0 (mod n 3))) 15 2 (range))))

  )