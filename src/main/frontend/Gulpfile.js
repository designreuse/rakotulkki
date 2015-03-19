'use strict';

var gulp = require('gulp'),
    jshint = require('gulp-jshint'),
    jscs = require('gulp-jscs'),
    source = require('vinyl-source-stream'),
    browserify = require('browserify'),
    concat = require('gulp-concat'),
    less = require('gulp-less'),
    path = require('path'),
    autoprefixer = require('gulp-autoprefixer'),
    refresh = require('gulp-livereload'),
    nodemon = require('gulp-nodemon'),
    uglify = require('gulp-uglify'),
    clean = require('gulp-clean'),
    bower = require('gulp-bower');

//var expressServer = require('./server');
//gulp.task('serve_', function() {
//    console.log('Server');
//    expressServer.startServer();
//});

//gulp.task('serve', function () {
//    nodemon({ script: 'server.js', ext: 'json js', ignore: ['public/*', 'client/*'] })
//        .on('change', ['lint'])
//        .on('change', ['jscs'])
//        .on('restart', function () {
//            console.log('Restarted webserver')
//        });
//});

// Dev task
gulp.task('dev', ['views', 'styles', 'lint', 'jscs', 'browserify', 'watch'], function () {
});

// Build task
// JSCS seems to not work here, maybe because it needs to be installed separately
// and the maven plugin uses a separate node install than the system.
// Aaaaanyhoo, FIXME/TODO!
//gulp.task('build', ['views', 'styles', 'lint', 'browserify-production', 'compress'], function() {
gulp.task('build', ['views', 'styles', 'browserify', 'copy-bower-components'], function () {
    // Clean up the temporary javascript after uglify
    return gulp.src('tmp', {read: false})
        .pipe(clean());
});

// Uglify
gulp.task('compress', function () {
    gulp.src('tmp/core.js')
        .pipe(uglify())
        .pipe(gulp.dest('../resources/public/js/'))
});

// JSLint task
gulp.task('lint', function () {
    gulp.src('client/scripts/*.js')
        .pipe(jshint())
        .pipe(jshint.reporter('default'));
});

gulp.task('jscs', function () {
    return gulp.src(['client/scripts/**/*.js'])
        .pipe(jscs());
});

gulp.task('copy-bower-components', function () {
    bower('./client/bower_components/')
        .pipe(gulp.dest('../resources/public/js/lib/'));
});

// Styles task
gulp.task('styles', function () {
    gulp.src('./client/styles/main.less')
        .pipe(less({
            // not getting this paths -thingy to work, so as a workaround all bootstrap imports have relative paths in
            // them
            //paths: ['./node_modules/'],
            onError: function (e) {
                console.log(e);
            }
        }))
        .pipe(gulp.dest('../resources/public/css/'));
});

// Browserify task
gulp.task('browserify', function () {
    var bundleStream = browserify({
        entries: ['./client/scripts/main.js'],
        insertGlobals: true,
        debug: true
    }).bundle().pipe(source('core.js'));
    return bundleStream.pipe(gulp.dest('../resources/public/js'));
});

// Browserify production task
gulp.task('browserify-production', function () {
    var bundleStream = browserify({
        entries: ['./client/scripts/main.js'],
        debug: true
    }).bundle().pipe(source('core.js'));
    return bundleStream.pipe(gulp.dest('./tmp'));
});

// Views task
gulp.task('views', function () {
    // Get our index.html
    gulp.src('client/index.html')
        // And put it in the public folder
        .pipe(gulp.dest('../resources/public/'));

    // Any other view files from client/views
    gulp.src('client/views/**/*')
        // Will be put in the public/views folder
        .pipe(gulp.dest('../resources/public/views/'));
});

gulp.task('watch', ['serve', 'lint'], function () {
    // Start live reload server
    refresh.listen();

    // Watch our scripts, and when they change run lint and browserify
    gulp.watch(['client/scripts/*.js', 'client/scripts/**/*.js'], [
        'lint',
        'jscs',
        'browserify'
    ]);

    // Watch our sass files
    gulp.watch(['client/styles/**/*.less'], [
        'styles'
    ]);

    // Watch view files
    gulp.watch(['client/**/*.html'], [
        'views'
    ]);

    gulp.watch('../resources/public/**').on('change', refresh.changed);

});

gulp.task('default', ['dev']);
