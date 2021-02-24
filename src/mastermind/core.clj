(ns mastermind.core
  (:require [mastermind.scorer :refer :all]
            [clojure.string :as str]))

(defn generate-random-code []
  [
   (rand-int 6)
   (rand-int 6)
   (rand-int 6)
   (rand-int 6)
   ])

(defn get-user-input-guess []
  (do
    (println "Please input your guess: ")
    (let [user-input (read-line)
          guess-string (str/split user-input #" ")]
      (map #(Integer/parseInt %) guess-string))
    )
  )

(defn check-win [score] (= [4 0] score))

(defn game
  ([]
   (game (generate-random-code) (get-user-input-guess) [])
   )
  ([code guess guess-history]
   (let [current-score (score code guess)
         is-win (check-win current-score)
         new-history (conj guess-history {:guess guess :score current-score})]
     (println (str "Your score is: " current-score))
     (if is-win
       (println (str "You win in " (count new-history) " move(s)"))
       (recur code (get-user-input-guess) new-history))
     )
   )
  )

(defn -main [] (game))