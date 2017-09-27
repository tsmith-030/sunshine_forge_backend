Feature('My first test');

Scenario('Test app renders on home screen', (I) => {
    I.amOnPage('/');
    I.click("Add Space");
    I.fillField('Name', 'A');
    I.fillField('Memory', '100');
    I.fillField('Disk', '200');
    I.click('Create');
    I.see("A");
});
