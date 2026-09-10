(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest dji-has-spec-basis
  (let [sb (facts/spec-basis "DJI")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (some? (facts/rep-spec-basis "DJI")))
    (is (some? (facts/corporate-number-spec-basis "DJI")))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "DJI")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "DJI" all)))
    (is (not (facts/required-evidence-satisfied? "DJI" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["DJI" "USA" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 2 (:covered c)))
    (is (= ["ATL"] (:missing-jurisdictions c)))))

(deftest national-spec-names-boamp-bulletin-not-a-transactional-portal
  (testing "the dossier only grounds BOAMP, an official bulletin -- do not claim a transactional e-procurement portal for DJI"
    (let [sb (facts/spec-basis "DJI")]
      (is (re-find #"(?i)boamp" (:national-spec sb)))
      (is (re-find #"marchespublics\.gouv\.dj" (:national-spec sb)))
      (is (re-find #"(?i)no verified transactional" (:national-spec sb))
          "must explicitly disclaim a transactional portal, not just omit one"))))
