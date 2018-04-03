import PropTypes from 'prop-types';
import {requireNativeComponent, ViewPropTypes} from 'react-native';

var iface = {
  name: 'View',
  propTypes: {
    dataSource: PropTypes.any,
    snapToColumnsWhenDragging: PropTypes.bool,
    snapToColumnsWhenScrolling: PropTypes.bool,
    snapDragItemToTouch: PropTypes.bool,
    snapToColumnInLandscape: PropTypes.bool,
    ...ViewPropTypes, // include the default view properties
  },
};

module.exports = requireNativeComponent('KanbanBridge', iface);



