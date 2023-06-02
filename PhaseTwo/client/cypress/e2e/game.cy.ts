// Testing Strategy: Client side end to end testing
// Partitions: game have a random puzzle loaded and visible, game have a random solution loaded but not visible, game have a random puzzle not loaded and not visible
// Subdomains: game have a random puzzle loaded and visible, game have a random solution loaded but not visible
describe('Game', () => {
    it('should have random puzzle image loaded and visible', () => {
        cy.visit('/');

        cy.get('.game .flip-card .puzzle img').each((img) => {
            let image: HTMLImageElement = (img[0] as HTMLImageElement);
            cy.wait(10000);
            cy.wrap(image).should('be.visible');
            expect(image.naturalWidth).to.be.greaterThan(0);
            expect(image.naturalHeight).to.be.greaterThan(0);
        });
    });

    it('should have random solution image loaded but not visible', () => {
        cy.visit('/');

        cy.get('.game .flip-card .solution img').each((img) => {
            let image: HTMLImageElement = (img[0] as HTMLImageElement);
            cy.wait(10000);
            cy.wrap(image).should('not.be.visible');
            expect(image.naturalWidth).to.be.greaterThan(0);
            expect(image.naturalHeight).to.be.greaterThan(0);
        });
    });
});