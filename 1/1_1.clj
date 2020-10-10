;; solution - combine func
(defn foreach 
	([base sym] (foreach base sym (list)))
	([base sym res]
		(if (> (count base) 0)
            (if (= (first (first base)) sym)
                (recur (rest base) sym res)
                (recur (rest base) sym (cons (conj (first base) sym) res)))
            res)))
(defn create 
	([alph base] (create alph base 0 (list)))
	([alph base index res]
    (if (< index (count alph))
        (recur alph base (inc index) (concat (foreach base (nth alph index)) res))
        res)))    
(defn combine 
    ([alph, n] (combine alph n `(())))
    ([alph, n, res] 
        (if (> n 0) 
            (recur alph (dec n) (create alph res))
            res
    )))