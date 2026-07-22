# cloud-itonami-iso3166-dji

**`:implemented`** for **DJI** (Djibouti). Flagship check `rccm-entity-missing`,
corporate-number-equivalent check `rccm-registration-unverified`.

Independent Public-Sector Market-Entry & Procurement Compliance Service:
a MarketEntry-LLM advisor sealed behind a langgraph-clj `StateGraph`,
censored by a 7-check Market-Entry Compliance Governor, with an
append-only audit ledger and a 0→3 phase rollout gate. See
`docs/business-model.md` for the Trust Controls this actor enforces and
`docs/operator-guide.md` for the human-operator workflow.

```
clojure -M:dev:test   # run the full test suite
clojure -M:lint       # clj-kondo, errors fail
clojure -M:dev:run    # demo driver (marketentry.sim)
```

Regulatory grounding (verified against `.dj` government domains and the
ILO NATLEX legal database -- see `src/marketentry/facts.cljc` for full
citations):

- **CNMP** -- Commission Nationale des Marchés Publics, the public
  procurement regulator. `https://marchespublics.gouv.dj/`
- **Procurement law** -- Loi n°53/AN/09/6ème L portant Code des Marchés
  Publics (1 July 2009).
- **RCCM** -- Registre du Commerce et du Crédit Mobilier, Djibouti's
  commercial/business registration record, under the OHADA treaty
  framework, administered through the CFE (Centre de Formalités des
  Entreprises) single administrative window.
- **BOAMP** -- the official public-procurement announcement bulletin
  CNMP maintains on `marchespublics.gouv.dj`. There is no verified
  evidence of a transactional national e-procurement portal beyond this
  bulletin -- this actor does not claim one.

AGPL-3.0-or-later.

## Culture catalog

Alongside the market-entry catalog, this repo carries a **country-level
regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products,
beverages, crafts, festivals and heritage sites for Djibouti:

- `src/culture/facts.cljc` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `marketentry.facts`' convention).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalog: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
