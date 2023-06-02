// Testing Strategy: Client side end to end testing
// partitions: app runs, app has a title, app doesn't run, app doesn't have a title
// Subdomains: app runs and app have a title
describe('App', () => {
  it('runs', () => {
    cy.visit('/')
  })
  it('has a title', () => {
    cy.visit('/')
    cy.title().should('include', 'SE Club puzzles')
  })
})