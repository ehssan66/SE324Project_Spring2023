// Testing Strategy: Client side end to end testing
// Partitions:
//      header has a title,
//      header doesn't have a title,
//      header has a link to the home page,
//      header doesn't have a link to the home page,
//      header has the title of the current page,
//      header doesn't have the title of the current page,
//      header has the scoreboard and switch theme buttons,
//      header doesn't have the scoreboard and switch theme buttons,
//      header switches theme when the button is clicked,
//      header doesn't switch theme when the button is clicked
//      header switches theme when clicked twice,
//      header doesn't switch theme when clicked twice
// Subdomains:
//      header has a title,
//      header has a link to the home page,
//      header has the title of the current page,
//      header has the scoreboard and switch theme buttons,
//      header switches theme when the button is clicked,
//      header switches theme when clicked twice
describe('Header', () => {
    it('has a title', () => {
        cy.visit('/')
        cy.title().should('include', 'SE Club puzzles')
    });

    it('has a link to the home page', () => {
        cy.visit('/')
        cy.contains('header a.btn', 'SE Club').should('have.attr', 'href', '/');
    });

    it('has the title of the current page', () => {
        cy.visit('/')
        cy.contains('header a.btn', 'SE Club')
        cy.contains('header a.btn small', 'puzzles');
    });

    it('has the scoreboard button and switch theme button', () => {
        cy.visit('/')
        cy.get('header button.scoreboard').should('have.attr', 'title', 'Scoreboard');
        cy.get('header button.switch-theme').should('have.attr', 'title', 'Switch Theme');
    });

    it('switches theme when the button is clicked', () => {
        cy.visit('/')
        cy.get('header button.switch-theme').click();
        cy.get('body').should('have.data', 'theme', 'white');
    });

    it('switches back theme when the button is clicked again', () => {
        cy.visit('/')
        cy.get('header button.switch-theme').click();
        cy.get('header button.switch-theme').click();
        cy.get('body').should('have.data', 'theme', 'dark');
    });
});