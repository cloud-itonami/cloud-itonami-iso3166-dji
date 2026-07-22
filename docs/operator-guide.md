# Operator guide — DJI

Human-gated filing only. Every `:filing/draft`/`:filing/submit`
proposal always pauses for a human market-entry operator's approval —
there is no rollout phase in which either auto-commits (see
`src/marketentry/phase.cljc`).

## Portal / channel

**CNMP** (Commission Nationale des Marchés Publics) —
`https://marchespublics.gouv.dj/` — examines pre-qualification and
tender-dossier conformity before a call for competition is launched,
and publishes **BOAMP** (Bulletin Officiel des Annonces de Marchés
Publics), the official procurement-announcement bulletin. There is no
verified evidence of a transactional national e-procurement portal
beyond BOAMP — this actor tracks announcements/registration evidence,
it does not submit through a portal API that does not exist.

## Workflow

1. `:engagement/intake` — record the operator, portal, and fee terms
   for a new engagement. May auto-commit at phase 3 once the governor
   is clean (low real-world risk — no filing has happened yet).
2. `:jurisdiction/assess` — request the DJI evidence checklist. ALWAYS
   requires human approval, even when clean (governor `escalate?` is
   forced true for this operation regardless of phase).
3. `:filing/draft` — prepare the internal filing-draft record.
   Requires a completed assessment on file (`evidence-incomplete`
   otherwise). ALWAYS requires human approval.
4. `:filing/submit` — prepare the filing-submit record, the step that
   corresponds to an actual real-world CNMP/BOAMP-facing action.
   ALWAYS requires human approval, and is independently checked
   against:
   - `rccm-entity-missing` — is a Djibouti-registered entity (company,
     Branch, or Representative Office recorded with RCCM) actually on
     file when the engagement declares one is required?
   - `engagement-fee-mismatch` — does the claimed fee actually equal
     `base-fee + monthly-rate × monitoring-months`?
   - `rccm-registration-unverified` — has the RCCM registration record
     itself actually been independently verified, when the engagement
     declares verification is required?
   - `already-drafted` / `already-submitted` — refuses to double-file
     the same engagement.

Any HARD violation above is a hold NO approver can override — the
operator must fix the underlying engagement record (verify the RCCM
entity, correct the fee, verify the RCCM registration) before
resubmitting, not approve past the governor.

## Required evidence checklist (per `src/marketentry/facts.cljc`)

- RCCM registration record (OHADA)
- CNMP tender-dossier conformity record
- Tribunal de Commerce filing (share capital / director / UBO
  disclosure)
- Authorized-representative record
