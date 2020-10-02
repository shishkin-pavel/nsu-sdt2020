;; solution - combine func
(defn create 
	([alph base] (create alph base 0 (list)))
	([alph base index res]
    (if (< index (count alph))
        (recur alph base (inc index) (concat (map (fn [x] (.concat x (nth alph index))) (filter (fn [x] (not (.endsWith x (nth alph index)))) base)) res))
        res)))	
(defn combine 
	([alph, n] (combine alph (dec n) alph))
	([alph, n, res] 
	    (if (> n 0) 
            (recur alph (dec n) (create alph res))
            res
    )))