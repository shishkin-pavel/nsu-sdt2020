(ns laba2-1.core
  (:gen-class))

(defn mini [fun x0 x1 dx]
  (* (* (+ (fun x0) (fun x1)) 0.5) dx))


(defn integral [fun x1 dx]
  (let [x0 (- x1 dx)]
    (if (not= x1 0) (+ (mini fun x0 x1 dx) (integral-m fun x0 dx))
        (mini fun x0 x1 dx))))

(def integral-m (memoize integral))

(def f #(* % %))
(defn -main []
  (print "num:100 -")
  (time (integral f 100 1))
  (println "---------")
  (print "mem num:100 -")
  (time (integral-m f 100 1))
  (print "mem num:100 -")
  (time (integral-m f 110 1))
  (print "mem num:1100 -")
  (time (integral-m f 100 1))
  (print "mem num:1200 -")
  (time (integral-m f 120 1))
  (print "mem num:1300 -")
  (time (integral-m f 130 1))
  (print "mem num:1400 -")
  (time (integral-m f 140 1))
  (print "mem num:1500 -")
  (time (integral-m f 150 1)))
(-main)