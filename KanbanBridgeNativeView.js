import PropTypes from 'prop-types';
import {requireNativeComponent, ViewPropTypes} from 'react-native';

var iface = {
  name: 'View',
  propTypes: {
    ...ViewPropTypes, // include the default view properties
  },
};

module.exports = requireNativeComponent('KanbanBridge', iface);



