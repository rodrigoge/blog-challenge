import 'tailwindcss/tailwind.css';
import '../styles/global.css';

export function GetCommentaries() {
  return (
    <div className="w-10/12 h-auto flex justify-between border rounded-lg border-gray-300 p-5 m-3">
      <div className='overflow-ellipsis whitespace-nowrap overflow-hidden'>
        <h1 className="font-bold">
          Post title
        </h1>
      </div>
    </div>
  );
}