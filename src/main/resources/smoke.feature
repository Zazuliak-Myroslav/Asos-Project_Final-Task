Feature: Smoke
  As a user
  I want to test all main site functionality
  So that I can be sure that site works correctly

  Scenario Outline: Choice of preferences
    Given User opens '<page>' page
    When User click on flag on top bar
    And User choice country in '<country>' SHOP IN in drop down list
    And User choice currency in '<currency>' CURRENCY in drop down list
    And User click on UPDATE PREFERENCES button
    And User click on flag on top bar
    Then User checks that '<country>' country and '<currency>' currency are chosen correctly

    Examples:
      | page                      | country | currency |
      | https://www.asos.com/men/ | Ukraine | $ USD    |

  Scenario Outline: Search by keyword
    Given User opens '<page>' page
    When User makes search by keyword '<keyword>'
    And User click on search button
    Then User check that search was performed by '<keyword>' keyword search

    Examples:
      | page                      | keyword |
      | https://www.asos.com/men/ | suit    |

  Scenario Outline: Search by keyword with autocomplete
    Given User opens '<page>' page
    When User makes search by keyword '<keyword>'
    And User selects '<number option>' options from search autocomplete list
    Then User check that search was performed by search autocomplete

    Examples:
      | page                      | keyword | number option |
      | https://www.asos.com/men/ | su      | 1             |

  Scenario Outline: Choice random product by categories
    Given User opens '<page>' page
    And User selects different gender
    And User checks if gender has changed
    When User selects category
    Then User checks if search by selected category has taken place

    Examples:
      | page                        |
      | https://www.asos.com/men/   |

  Scenario Outline: Add product to wishlist
    Given User opens '<page>' page
    And User makes search by keyword '<keyword>'
    And User click on search button
    And User click on '<number product>' product
    When User click on heart icon
    And User click on heart icon on top bar
    Then User checks if product is added to wishlist

    Examples:
      | page                      | keyword | number product |
      | https://www.asos.com/men/ | suit    | 23             |

  Scenario Outline: Delete product from wishlist
    Given User opens '<page>' page
    And User makes search by keyword '<keyword>'
    And User click on search button
    And User click on '<number product>' product
    And User click on heart icon
    And User click on heart icon on top bar
    When User click on delete button from wishlist
    Then User checks if product is deleted from wishlist

    Examples:
      | page                      | keyword | number product |
      | https://www.asos.com/men/ | suit    | 23             |

  Scenario Outline: Add product to cart
    Given User opens '<page>' page
    And User makes search by keyword '<keyword>'
    And User click on search button
    When User click on '<number product>' product
    And User selects size on clothing
    And User click on ADD TO BAG button
    And User click on bag icon on top bar
    And User click on view bag button
    Then User checks if product is added to cart

    Examples:
      | page                      | keyword | number product |
      | https://www.asos.com/men/ | suit    | 23             |

  Scenario Outline: Delete product from cart
    Given User opens '<page>' page
    And User makes search by keyword '<keyword>'
    And User click on search button
    When User click on '<number product>' product
    And User selects size on clothing
    And User click on ADD TO BAG button
    And User click on bag icon on top bar
    And User click on view bag button
    And User click on delete button from cart
    Then User checks if product is deleted from cart

    Examples:
      | page                      | keyword | number product |
      | https://www.asos.com/men/ | suit    | 23             |

  Scenario Outline: Move product from wishlist to cart
    Given User opens '<page>' page
    And User makes search by keyword '<keyword>'
    And User click on search button
    And User click on '<number product>' product
    And User selects size on clothing
    And User click on heart icon
    And User click on heart icon on top bar
    When User click on MOVE TO BAG button
    And User click on bag icon on top bar
    And User click on view bag button
    Then User checks if product is added to cart from wishlist

    Examples:
      | page                      | keyword | number product |
      | https://www.asos.com/men/ | suit    | 23             |

  Scenario Outline: Move product from cart to wishlist
    Given User opens '<page>' page
    And User makes search by keyword '<keyword>'
    And User click on search button
    And User click on '<number product>' product
    And User selects size on clothing
    And User click on ADD TO BAG button
    And User click on bag icon on top bar
    And User click on view bag button
    When User click on Save for later button
    And User click on heart icon on top bar
    Then User checks if product is added to wishlist from cart

    Examples:
      | page                      | keyword | number product |
      | https://www.asos.com/men/ | suit    | 23             |

