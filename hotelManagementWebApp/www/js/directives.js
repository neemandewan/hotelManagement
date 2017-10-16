'use strict'

app.directive('aTemplate', function(){
    return {
        template: '<div class="anime" ng-include="getTemplateUrl()"></div>',
        replace: true,
        link: function(scope, element, attrs) {
        }

    }
});

app.animation('.anime', function() {
  var getScope = function(e) {
    return angular.element(e).scope();
  };
  return {
    enter : function(element, done) {
        var scope = getScope(element);
        scope.animNumber = (scope.animNumber) ? scope.animNumber : 45;
        $('.effect_' + scope.animNumber).trigger('click');
        return false;
    }
  };
});

app.directive('material', function() {
  return {
    // A = attribute, E = Element, C = Class and M = HTML Comment
    restrict:'A',
    link: function(scope, element, attrs) {
      $.material.init();
    }
  };
});

app.directive('initNav', function() {
  return {
    // A = attribute, E = Element, C = Class and M = HTML Comment
    restrict:'A',
    link: function(scope, element, attrs) {
      setTimeout(function() {
        new gnMenu( document.getElementById( 'gn-menu' ));  
      }, 10);
    }
  };
});

app.directive("owlCarousel", function() {
    return {
        restrict: 'E',
        transclude: false,
        link: function (scope) {
            scope.initCarousel = function(element) {
              // provide any default options you want
                var defaultOptions = {
                  itemsCustom : [
                      [0, 1],
                      [450, 1],
                      [600, 1],
                      [700, 1],
                      [1000, 1],
                      [1200, 1],
                      [1400, 1],
                      [1600, 1]
                  ],
                  navigation : false,
                  autoPlay: true,
                  stopOnHover: true,
                  slideSpeed : 200,
                  paginationSpeed : 500
                };
                var customOptions = scope.$eval($(element).attr('data-options'));
                // combine the two options objects
                for(var key in customOptions) {
                    defaultOptions[key] = customOptions[key];
                }
                // init carousel
                $(element).owlCarousel(defaultOptions);
            };
        }
    };
})
app.directive('owlCarouselItem', [function() {
    return {
        restrict: 'A',
        transclude: false,
        link: function(scope, element) {
          // wait for the last item in the ng-repeat then call init
            if(scope.$last) {
                scope.initCarousel(element.parent());
            }
        }
    };
}]);
