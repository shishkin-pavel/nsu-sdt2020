(ns parallel-lazy-filter
    (:gen-class))
(defn parallel-filter "thread_count - how much threads at max, slice_size - how big are slices for each thread"
    ([pred coll] (parallel-filter pred coll (+ 2 (.. Runtime getRuntime availableProcessors)) 5))
    ;unfortunately no implicit overload through default arguments
    ([pred coll thread_count slice_size] 
        (lazy-cat 
        (let 
            [slice (take (* thread_count slice_size) coll)] 
            (->> slice
            (partition-all thread_count)
            (map #(future (doall (filter pred %))))
            (doall)
            (map deref)
            (reduce concat)
            )) 
        (parallel-filter pred (drop (* thread_count slice_size) coll) thread_count slice_size))))