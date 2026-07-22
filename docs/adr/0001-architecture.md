# ADR-0001: DJI

Mirrors `cloud-itonami-iso3166-ago`'s architecture (langgraph-clj
StateGraph OperationActor, sealed MarketEntry-LLM advisor, independent
Market-Entry Compliance Governor, Store protocol with MemStore +
DatomicStore, 0→3 phase rollout gate, append-only audit ledger), with
Djibouti-specific field names and governor checks in place of Angola's:

- `rccm-entity-missing` (flagship jurisdiction-specific check) in place
  of `ao-entity-missing`. Field names: `:requires-rccm-entity?` /
  `:has-rccm-entity?` in place of `:requires-ao-entity?` /
  `:has-ao-entity?`.
- `rccm-registration-unverified` (corporate-number/tax-ID-equivalent
  check) in place of `nif-unverified`. Field names:
  `:requires-rccm-registration?` / `:rccm-registration-verified?` in
  place of `:requires-nif?` / `:nif-verified?`. Djibouti has no
  documented separate national tax-ID scheme in the verified research
  dossier, so this actor verifies the RCCM registration record itself
  rather than inventing a distinct tax-ID.

`marketentry.store`'s `DatomicStore` is built directly on
`io.github.kotoba-lang/langchain-store` (`ls/enc`/`ls/dec*`/
`ls/identity-schema`/`ls/read-stream`/`ls/append-blob!`) from the
start, rather than a hand-rolled `enc`/`dec*` codec (ADR-2607141600: ~190
cloud-itonami actors hand-roll that exact two-liner; new stores must
not add to that count).

Every fact in `src/marketentry/facts.cljc` is grounded in the verified
Djibouti research dossier only — CNMP (Commission Nationale des Marchés
Publics), the 1 July 2009 Code des Marchés Publics, the CNMP
composition decree, RCCM/OHADA/CFE, and the BOAMP announcement
bulletin. No national e-procurement transactional portal is claimed —
the dossier only grounds the BOAMP bulletin.
