(defn my-map
    [func in_list] (reduce (fn [x y] (concat x [(func y)])) (cons (list) in_list)))
(defn my-filter
    [func in_list] (reduce (fn [x y] (if (func y)(concat x [y]) x)) (cons (list) in_list)))