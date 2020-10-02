;; question - is reduce supposed to be used as list reversal?
(defn my-map
    ([func in_list] (my-map func in_list 0 (list)))
    ([func in_list index out_list] 
        (if (< index (count in_list))
            (recur func in_list (inc index) (concat out_list [(func (nth in_list index))]))
            out_list)))
(defn my-filter
    ([func in_list] (my-filter func in_list 0 (list)))
    ([func in_list index out_list] 
        (if (< index (count in_list))
            (if (func (nth in_list index))
                (recur func in_list (inc index) (concat out_list [(nth in_list index)]))
                (recur func in_list (inc index) out_list))
            out_list)))