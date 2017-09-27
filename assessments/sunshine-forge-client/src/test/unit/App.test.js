import React from 'react';
import ReactDOM from 'react-dom';
import App from '../../main/App';
import {shallow, mount} from 'enzyme';

describe('Given a sunshine forge app', () => {
    it('then an "Add Space" button appears', () => {
        let wrapper = shallow(<App />)

        expect(wrapper.find('#add-space').text()).toEqual("Add Space")
    });

});
