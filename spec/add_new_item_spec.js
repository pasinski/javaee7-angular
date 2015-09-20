'use strict';

describe('Add new person', function() {
    it('should add a new person to the form', function(){
        browser.get('http://michal:57802/javaee7-angular-3.5/');
        var clearBtn = element(by.id('clear-btn'));
        var submitBtn = element(by.id('submit-btn'));
        var nameInput = element(by.model('person.name'));
        var alerts = element.all(by.repeater('alert in alerts'));


        clearBtn.click();
        nameInput.sendKeys('Name to add and remove later on');
        element(by.model('person.description')).sendKeys('A description to add');
        element(by.model('person.imageUrl')).sendKeys('http://img1.wikia.nocookie.net/__cb20140616090940/naruto/images/thumb/b/b3/KakashiHatake.png/300px-KakashiHatake.png');
        submitBtn.click();

        expect(alerts.count()).toBeGreaterThan(0);
        expect(alerts.filter(function(elem, indes){
            return elem.getText().then(function(text){
                console.log(text);
                return text.indexOf('Record saved successfully!') > -1
            })
         }).count()).toBeGreaterThan(0);



         //go to last page and remove added element
         element(by.linkText('Last')).click();

         //expect(element.all(by.binding('row.entity.name')).last()).toEqual('Name to add and remove later on');
         element.all(by.css('.glyphicon-remove')).last().click();

    })
});