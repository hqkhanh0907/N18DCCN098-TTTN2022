import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'castFilter' })
export class CastFilterPipe implements PipeTransform {
  /**
   * Pipe filters the list of elements based on the search text provided
   *
   * @param items list of elements to search in
   * @param searchText search string
   * @returns list of elements filtered by search text or []
   */
  transform(items: any[], searchText: string): any[] {
    if (!items) {
      return [];
    }
    if (!searchText) {
      return items;
    }
    searchText = searchText.toLocaleLowerCase();

    return items.filter((item: any) => {
      if (item.name.toLowerCase().includes(searchText)) {
        return item;
      }
    });
  }
}
@Pipe({ name: 'accFilter' })
export class AccountFilterPipe implements PipeTransform {
  /**
   * Pipe filters the list of elements based on the search text provided
   *
   * @param items list of elements to search in
   * @param searchText search string
   * @returns list of elements filtered by search text or []
   */
  transform(items: any[], searchText: string): any[] {
    if (!items) {
      return [];
    }
    if (!searchText) {
      return items;
    }
    searchText = searchText.toLocaleLowerCase();

    return items.filter((item: any) => {
      if (item.username.toLowerCase().includes(searchText)) {
        return item;
      }
    });
  }
}
@Pipe({ name: 'genreFilter' })
export class GenreFilterPipe implements PipeTransform {
  /**
   * Pipe filters the list of elements based on the search text provided
   *
   * @param items list of elements to search in
   * @param searchText search string
   * @returns list of elements filtered by search text or []
   */
  transform(items: any[], searchText: string): any[] {
    if (!items) {
      return [];
    }
    if (!searchText) {
      return items;
    }
    searchText = searchText.toLocaleLowerCase();

    return items.filter((item: any) => {
      if (item.name.toLowerCase().includes(searchText)) {
        return item;
      }
    });
  }
}
  @Pipe({ name: 'promoFilter' })
  export class PromoFilterPipe implements PipeTransform {
    /**
     * Pipe filters the list of elements based on the search text provided
     *
     * @param items list of elements to search in
     * @param searchText search string
     * @returns list of elements filtered by search text or []
     */
    transform(items: any[], searchText: string): any[] {
      if (!items) {
        return [];
      }
      if (!searchText) {
        return items;
      }
      searchText = searchText.toLocaleLowerCase();
  
      return items.filter((item: any) => {
        if (item.code_name.toLowerCase().includes(searchText)) {
          return item;
        }
      });
    }
}

@Pipe({
  name: 'Suff'
})
export class ThousandSuffixesPipe implements PipeTransform {

  transform(input: any, args?: any): any {
    var exp, rounded,
      suffixes = ['k', 'M', 'G', 'T', 'P', 'E'];

    if (Number.isNaN(input)) {
      return null;
    }

    if (input < 1000) {
      return input;
    }

    exp = Math.floor(Math.log(input) / Math.log(1000));

    return (input / Math.pow(1000, exp)).toFixed(args) + suffixes[exp - 1];


  }

}