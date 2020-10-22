(ns lab1.core
  (:gen-class))

(defn calc-trap [f a b]
    ;(println "calc-trap" a b)
  (* (/ (+ (f a) (f b)) 2) (- b a)))


(let [maxx 10 delta 2]

    (take (/ maxx delta)
        (iterate #(+ % delta) 0)
    )
)

(let [step 5 begin 10]
(let [ls (iterate #(+ % step) begin)]
    (take 10 ls)
)
)


(println ((fn [x] 1) 2))
(println ((fn [x] 1) 100))


(defn get-integrator [f begin step]
    (let [ls (iterate #(+ % (calc-trap f % (+ % step)))
                      begin)]
        (fn [k]
            (nth ls (/ k step))
            ;(take (/ k step) ls)
        )
    )
)

(let [integrator (get-integrator (fn [x] 2) 0 1)]
    (time (integrator 10))
    (time (integrator 10))
)


;;; Evaluating file: core.clj
;;"Elapsed time: 0.0376 msecs"
;;"Elapsed time: 0.0054 msecs"
;;(0 1/2 3/2 7/2 15/2 31/2 63/2 127/2 255/2 511/2)


;;(let [integrator (get-integrator #(* % 1) 0 5)]
;;    (time (integrator 10000))
;;    (time (integrator 10015))
;;    (time (integrator 09985))
;;)
