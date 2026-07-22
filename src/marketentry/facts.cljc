(ns marketentry.facts
  "Djibouti market-entry catalog.

  Every fact below is grounded in the verified research dossier for
  Djibouti (DJI) -- checked against `.dj` government domains and the
  ILO NATLEX legal database. This namespace states ONLY what that
  dossier confirms; it never fills a gap with a plausible-sounding
  invention (the same discipline `marketentry.governor`'s
  `spec-basis-violations` enforces downstream).

    - Procurement regulator: CNMP -- Commission Nationale des Marchés
      Publics. https://marchespublics.gouv.dj/
    - Procurement law: Loi n°53/AN/09/6ème L portant Code des Marchés
      Publics (1 July 2009).
      https://marchespublics.gouv.dj/reglement/24 (also indexed at ILO
      NATLEX: https://ilo.org/dyn/natlex/natlex4.detail?p_isn=81976&p_lang=fr)
    - CNMP's own mandate/composition: Décret N° 2010-0083/PRE (modifié
      par Décret N° 2020-198/PRE). https://marchespublics.gouv.dj/reglement/17
    - CNMP's role: examines pre-qualification and tender dossiers for
      conformity verification BEFORE a call for competition is
      launched; maintains BOAMP (Bulletin Officiel des Annonces de
      Marchés Publics) on its own site.
    - Business/commercial registration: RCCM (Registre du Commerce et
      du Crédit Mobilier), under the OHADA treaty framework (Djibouti
      is an OHADA member state); administered through the CFE (Centre
      de Formalités des Entreprises), described as the single
      administrative window (\"guichet unique\").
      https://rccm.ohada.org/staticPage/index?alias=rccm ,
      https://rccm.ohada.org/
    - Foreign entities may enter via a Branch or Representative Office
      without establishing a separate legal entity; a foreign national
      may serve as sole director of a locally incorporated company
      with no residency/local-work-permit requirement tied to that
      role specifically. Djibouti requires share capital, director/
      shareholder compliance, UBO disclosure, and KYC documents filed
      with the Tribunal de Commerce for ALL registered entity types.

  What this catalog deliberately does NOT claim: there is no verified
  evidence of a Djibouti-specific national e-procurement PORTAL beyond
  the BOAMP bulletin (unlike, e.g., Angola's SNCP e-procurement system
  or the USA's SAM.gov) -- `:national-spec` names the BOAMP bulletin,
  not a transactional e-procurement portal. Do not add one."
  )

(def catalog
  {"DJI" {:name "Djibouti"
          :owner-authority "CNMP — Commission Nationale des Marchés Publics"
          :legal-basis "Loi n°53/AN/09/6ème L portant Code des Marchés Publics (1 July 2009); CNMP mandate/composition per Décret N° 2010-0083/PRE (modifié par Décret N° 2020-198/PRE)"
          :national-spec "BOAMP (Bulletin Officiel des Annonces de Marchés Publics) via marchespublics.gouv.dj — no verified transactional e-procurement portal beyond this bulletin"
          :provenance "https://marchespublics.gouv.dj/reglement/24"
          :required-evidence ["RCCM registration record (OHADA)"
                              "CNMP tender-dossier conformity record"
                              "Tribunal de Commerce filing (share capital / director / UBO disclosure)"
                              "Authorized-representative record"]
          :rep-owner-authority "CNMP — Commission Nationale des Marchés Publics"
          :rep-legal-basis "Décret N° 2010-0083/PRE fixant les attributions, la composition, les modalités d'organisation et de fonctionnement de la CNMP (modifié par Décret N° 2020-198/PRE) -- CNMP examines pre-qualification and tender dossiers for conformity before a call for competition is launched"
          :rep-provenance "https://marchespublics.gouv.dj/reglement/17"
          :corporate-number-owner-authority "RCCM (Registre du Commerce et du Crédit Mobilier) via the Centre de Formalités des Entreprises (CFE)"
          :corporate-number-legal-basis "OHADA (Organisation pour l'Harmonisation en Afrique du Droit des Affaires) treaty framework -- Djibouti is an OHADA member state; RCCM registration administered through the CFE single administrative window (guichet unique)"
          :corporate-number-provenance "https://rccm.ohada.org/staticPage/index?alias=rccm"}
   "AGO" {:name "Angola" :owner-authority "SNCP / e-procurement" :legal-basis "Lei dos Contratos Públicos (Lei n.º 41/20, de 23 de dezembro)"
          :national-spec "e-procurement + NIF/Registo Comercial" :provenance "https://www.minfin.gov.ao/"
          :required-evidence ["NIF/Registo Comercial record" "e-procurement registration record" "Commercial registry extract" "Authorized-representative record"]}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR" :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
