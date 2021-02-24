(ns mastermind.scorer-test
  (:require [clojure.test :refer :all]
            [mastermind.scorer :refer :all]))

(deftest all-guess-fails
  (testing "All Guess Fails"
    (is (= [0 0] (score [0 0 0 0] [1 1 1 1])))))

(deftest one-position-correct
  (testing "First guess is correct position"
    (is (= [1 0] (score [0 0 0 0] [0 1 1 1])))))

(deftest two-position-correct
  (testing "Two numbers correct position"
    (is (= [2 0] (score [0 0 0 0] [0 1 1 0])))))

(deftest one-value-correct
  (testing "One correct value"
    (is (= [0 1] (score [1 2 3 4] [0 1 0 0])))))

(deftest some-position-and-value-correct
  (testing "Some position and value correct"
    (is (= [2 2] (score [1 2 3 4] [1 2 4 3])))))

(deftest duplicate-value-correct
  (testing "Duplicate value correct"
    (is (= [2 0] (score [1 2 3 4] [3 3 3 4])))
    (is (= [1 2] (score [1 1 2 3] [1 2 1 2])))
    (is (= [1 0] (score [3 3 3 3] [3 1 1 1])))))

(deftest degenerate-guess
  (testing "Degenerate guess"
    (is (= [1 0] (score [1 2 3 4] [1])))))