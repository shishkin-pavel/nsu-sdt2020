(ns clojure1.core)

(defn add_alphabet_to_word [started alphabet]
  (if (empty? alphabet)
    (list)
    (let [firs (first alphabet) first_in_started (first started) rest_form_started (add_alphabet_to_word started (rest alphabet))]
      (if (not= firs first_in_started)
        (cons (cons firs started) rest_form_started)
        rest_form_started))))

(defn add_new_combo [started alphabet]
  (let [combinations (add_alphabet_to_word (first started) alphabet)]
    (if (empty? (rest started))
      combinations
      (concat combinations (add_new_combo (rest started) alphabet)))))

(defn main_rec [started n alphabet]
  (if (= n 0)
    started
    (main_rec (add_new_combo started alphabet) (- n 1) alphabet)))

;; (defn create_started_list [alphabet]
;; (if (empty? (rest alphabet))
;; (list (list (first alphabet)))
;; (cons (list (first alphabet)) (create_started_list (rest alphabet)))) )

(defn resolution [n alphabet]
  (let [started (main_rec '() 1 alphabet)]
    (if (= n 0)
      started
      (main_rec started (- n 1) alphabet))))

(defn -main
  [& args]
  (println (resolution (Integer/parseInt (last args)) (drop-last args))))