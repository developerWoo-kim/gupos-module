import { globalPlugins } from '@fullcalendar/core/router.js';
export * from '@fullcalendar/core/router.js';
import interactionPlugin__default from '@fullcalendar/interaction/router.js';
export * from '@fullcalendar/interaction/router.js';
import dayGridPlugin from '@fullcalendar/daygrid/router.js';
import timeGridPlugin from '@fullcalendar/timegrid/router.js';
import listPlugin from '@fullcalendar/list/router.js';
import multiMonthPlugin from '@fullcalendar/multimonth/router.js';

globalPlugins.push(interactionPlugin__default, dayGridPlugin, timeGridPlugin, listPlugin, multiMonthPlugin);
