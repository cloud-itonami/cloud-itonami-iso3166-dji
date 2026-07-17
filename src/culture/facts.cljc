(ns culture.facts
  "Country-level regional-culture catalog for Djibouti (DJI) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). First facts namespace
  in this blueprint-stage repo; the marketentry/statute catalogs land with
  :implemented (ADR-2607141700). City-level counterparts live in the
  cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors the fleet's `statute.facts`
  convention); entries carry no :culture/municipality (that attribute is
  city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"DJI"
   [{:culture/id "dji.dish.fah-fah"
     :culture/name "Fah-fah"
     :culture/name-local "Soupe djiboutienne"
     :culture/country "DJI"
     :culture/kind :dish
     :culture/summary "Djiboutian spicy boiled beef soup, mostly eaten in the southern parts of the country as well as northern Somalia."
     :culture/url "https://en.wikipedia.org/wiki/Fah-fah"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "dji.dish.lahoh"
     :culture/name "Lahoh"
     :culture/country "DJI"
     :culture/kind :dish
     :culture/summary "Spongy pancake-like flatbread originating from the Horn of Africa and Yemen, eaten regularly in Djibouti and neighbouring countries."
     :culture/url "https://en.wikipedia.org/wiki/Lahoh"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "dji.dish.garoobey"
     :culture/name "Garoobey"
     :culture/country "DJI"
     :culture/kind :dish
     :culture/summary "Porridge prepared by soaking oats in milk and flavoured with cumin or other spices, part of Djiboutian cuisine."
     :culture/url "https://en.wikipedia.org/wiki/Djiboutian_cuisine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "dji.dish.sambusa"
     :culture/name "Sambusa"
     :culture/country "DJI"
     :culture/kind :dish
     :culture/summary "Triangular samosa-style snack typically filled with ground meat or fish and hot green chili pepper, a popular snack in Djiboutian cuisine."
     :culture/url "https://en.wikipedia.org/wiki/Djiboutian_cuisine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "dji.product.lake-assal-salt"
     :culture/name "Lake Assal salt"
     :culture/country "DJI"
     :culture/kind :product
     :culture/summary "Salt from Lake Assal, whose crystallised salt zone holds an estimated 300 million tonnes; salt extraction by Afar nomads established ancient caravan routes to the Ethiopian mountains, and the material was termed 'white gold'."
     :culture/url "https://en.wikipedia.org/wiki/Lake_Assal_(Djibouti)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "dji.heritage.lake-assal"
     :culture/name "Lake Assal"
     :culture/name-local "Lac Assal"
     :culture/country "DJI"
     :culture/kind :heritage
     :culture/summary "Crater lake in central-western Djibouti and the lowest point on land in Africa; a protected zone under Djibouti's National Environmental Action Plan of 2000, proposed to UNESCO as a World Heritage Site together with the Ardoukoba volcano."
     :culture/url "https://en.wikipedia.org/wiki/Lake_Assal_(Djibouti)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "dji.heritage.day-forest"
     :culture/name "Day Forest National Park"
     :culture/name-local "Forêt du Day"
     :culture/country "DJI"
     :culture/kind :heritage
     :culture/summary "National park in the Goda Mountains and Tadjourah Region of Djibouti, established in 1939, protecting one of the very few forested areas of one of the least forested countries on Earth."
     :culture/url "https://en.wikipedia.org/wiki/Day_Forest_National_Park"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-dji culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "DJI"))
                 " DJI entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
