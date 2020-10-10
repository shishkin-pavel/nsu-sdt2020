
(defn fact [n]
  ((fn [n acc]
     (if (= n 0) acc (recur (dec n) (* acc n))))
   n 1))
(fact 5)

(defn fact2 [n]
  (loop [n n acc 1]
    (if (= n 0) acc (recur (dec n) (* acc n)))))

(fact2 5)