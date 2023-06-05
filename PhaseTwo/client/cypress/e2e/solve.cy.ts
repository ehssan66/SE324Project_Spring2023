// Testing Strategy: Client side end to end testing
// Partitions:
//     solve button flips the card when clicked,
//     solve button doesn't flip the card when clicked
// Subdomains:
//     solve button flips the card when clicked
// describe('solve button', () => {

//    it('should flip the card when clicked', () => {
//        cy.visit('/');
//       cy.get('.game .flip-card').should('not.have.class', '[transform:rotateY(180deg)]');
//       cy.get('.game .solve-puzzle').should('be.visible');
//      cy.wait(1000);
//      cy.get('.game .solve-puzzle').click();
//      cy.wait(1000);
//      cy.get('.game .flip-card').should('have.class', '[transform:rotateY(180deg)]');
//  });
//});
