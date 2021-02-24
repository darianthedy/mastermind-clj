(ns mastermind.scorer)

(defn count-true [bools]
  (count (filter identity bools)))

(defn position-score [code guess]
  (count-true (map #(= %1 %2) code guess)))

(defn value-score [code guess]
  (count-true (map #(contains? (set code) %) guess)))

(defn count-filter [value coll]
  (count (filter #(= value %) coll)))

(defn repetition-diff [check code guess]
  (- (count-filter check guess) (count-filter check code)))

(defn repetition-diff-list [code guess]
  (map #(repetition-diff % code guess) (set code)))

(defn overcount-value-score [code guess]
  (->> (repetition-diff-list code guess)
       (filter pos?)
       (reduce +))
  )

(defn score [code guess]
  (let [p (position-score code guess)
        v (value-score code guess)
        o (overcount-value-score code guess)]
    [p (- v p o)])
  )