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

    return items.filter((item: any)  => {
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

    return items.filter((item: any)  => {
      if (item.username.toLowerCase().includes(searchText)) {
        return item;
      }
    });
  }
}
