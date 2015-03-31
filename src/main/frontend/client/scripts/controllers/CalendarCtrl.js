'use strict';

exports.inject = function (app) {
    app.controller('CalendarCtrl', exports.controller);
    return exports.controller;
};

exports.controller = function ($scope, $compile, $modal, uiCalendarConfig, SessionsService, CustomerService) {
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    /* event source that pulls from google.com */
    $scope.eventSource = {};
    //url: "https://www.google.com/calendar/feeds/fi-fi.finnish%23holiday%40group.v.calendar.google.com/public/basic"

    /* event source that contains custom events on the scope */
    $scope.events2 = [{
        "id": 1,
        "name": null,
        "customerId": 1,
        "customerName": "Hannu Hanhi",
        "start": "2015-03-22T14:45:00.000",
        "end": "2015-03-22T15:30:00.000",
        "recurring": false,
        "title": "Hannu Hanhi"
    }, {
        "id": 2,
        "name": null,
        "customerId": 3,
        "customerName": "Salattu Asiakas",
        "start": "2015-03-01T10:15:00.000",
        "end": "2015-03-01T11:00:00.000",
        "recurring": false,
        "title": "Salattu Asiakas"
    }];
    /* event source that calls a function on every view switch */
    $scope.eventsF = function (start, end, timezone, callback) {
        var s = new Date(start).getTime() / 1000;
        var e = new Date(end).getTime() / 1000;
        var m = new Date(start).getMonth();
        var events = [{
            title: 'Feed Me ' + m,
            start: s + (50000),
            end: s + (100000),
            allDay: false,
            className: ['customFeed']
        }];
        callback(events);
    };

    $scope.loadEvents = function (start, end, timezone, callback) {
        SessionsService.listEvents().success(function (data) {
            callback(data);
            //$scope.eventSources = [data];
            //uiCalendarConfig.calendars['myCalendar1'].fullCalendar('refetchEvents');
        })
    };

    /* alert on eventClick */
    $scope.alertOnEventClick = function (date, jsEvent, view) {
        console.log((date.title + ' was clicked '));
        $scope.alertMessage = (date.title + ' was clicked ');
    };
    /* alert on Drop */
    $scope.alertOnDrop = function (event, delta, revertFunc, jsEvent, ui, view) {
        $scope.alertMessage = ('Event Droped to make dayDelta ' + delta);
    };
    /* alert on Resize */
    $scope.alertOnResize = function (event, delta, revertFunc, jsEvent, ui, view) {
        $scope.alertMessage = ('Event Resized to make dayDelta ' + delta);
    };
    /* add and removes an event source of choice */
    $scope.addRemoveEventSource = function (sources, source) {
        var canAdd = 0;
        angular.forEach(sources, function (value, key) {
            if (sources[key] === source) {
                sources.splice(key, 1);
                canAdd = 1;
            }
        });
        if (canAdd === 0) {
            sources.push(source);
        }
    };
    /* add custom event*/
    $scope.addEvent = function () {
        $scope.events.push({
            title: 'Open Sesame',
            start: new Date(y, m, 28),
            end: new Date(y, m, 29),
            className: ['openSesame']
        });
    };

    $scope.listCustomers = function () {
        CustomerService.list().success(function (data) {
            $scope.customers = data;
        })
    };

    $scope.save = function () {
        console.log($scope.session);
        SessionsService.save($scope.session).success(function (data) {
            $location.path("/index");
        });
    };

    $scope.listCustomers();
    //$scope.loadEvents();

    $scope.dayClicked = function (date, jsEvent, view) {

        console.log('Clicked on: ' + date.format());

        // Pre-fetch an external template populated with a custom scope
        var myOtherModal = $modal({
            scope: $scope,
            content: 'Jehnaa',
            title: 'Luo sessio',
            template: 'views/modals/modalSession.html',
            show: true
        });
        //myOtherModal.$promise.then(myOtherModal.show);

    };

    /* remove event */
    $scope.remove = function (index) {
        $scope.events.splice(index, 1);
    };
    /* Change View */
    $scope.changeView = function (view, calendar) {
        uiCalendarConfig.calendars[calendar].fullCalendar('changeView', view);
    };
    /* Change View */
    $scope.renderCalender = function (calendar) {
        if (uiCalendarConfig.calendars[calendar]) {
            uiCalendarConfig.calendars[calendar].fullCalendar('render');
        }
    };
    /* Render Tooltip */
    $scope.eventRender = function (event, element, view) {
        element.attr({
            'tooltip': event.title,
            'tooltip-append-to-body': true
        });
        $compile(element)($scope);
    };
    /* config object */
    $scope.uiConfig = {
        calendar: {
            lang: 'fi',
            height: 450,
            editable: true,
            header: {
                left: 'title',
                center: '',
                right: 'today prev,next'
            },
            dayClick: $scope.dayClicked,
            eventClick: $scope.alertOnEventClick,
            eventDrop: $scope.alertOnDrop,
            eventResize: $scope.alertOnResize,
            eventRender: $scope.eventRender
        }
    };
    /* event sources array*/
    $scope.eventSources = [$scope.loadEvents];
}
