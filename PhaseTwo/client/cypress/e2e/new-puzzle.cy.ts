// Testing Strategy: Client side end to end testing
// Partitions:
//      new puzzle button fetches a random new puzzle when the button is clicked
//      new puzzle button doesn't fetch a random new puzzle when the button is clicked
// Subdomains:
//      new puzzle button fetches a random new puzzle when the button is clicked
describe('new puzzle button', () => {
    it('fetches a random new puzzle when the button is clicked', () => {
        cy.visit('/');

        cy.intercept({ url: 'http://localhost:3000/api/puzzles/suguru/random', middleware: true, method: 'GET'}, (req) => delete req.headers['if-none-match']).as('random')

        cy.get('.game button.new-puzzle').click();

        cy.wait('@random').its('response').then((response) => {
            cy.wrap(response?.statusCode).should('match', /200|304/)
            cy.wrap(response?.body).should('deep.include', { id: 1, link: "/public/suguru/puzzle-1.png" });
        });
    });
});